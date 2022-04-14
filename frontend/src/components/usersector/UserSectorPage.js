import UserSectorForm from "./UserSectorForm";
import React from "react";
import {getNewSession} from "../../actions/session.actions";
import {connect} from "react-redux";
import {getSectors} from "../../actions/sectors.action";
import {useBnIdle} from 'use-bn-idle';
import {NotificationManager} from 'react-notifications';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-notifications/lib/notifications.css';


function UserSectorPage({session, sectors, getNewSession, getSectors}) {
    const [startTimer, stopTimer] = useBnIdle(() => {
        window.location.reload();
    })
    const [notifyStartTimer, notifyStopTimer] = useBnIdle(() => {
        NotificationManager.warning("Session is expiring. Please submit data", "Warning", 4000)
    })

    React.useEffect(() => {
        if (session) {
            let now = new Date()
            let end = new Date(Date.parse(session.end))
            let timestampDiff = (end.getTime() - now.getTime())
            let timerSeconds = ((timestampDiff % 60000) / 1000).toFixed(0);
            startTimer(timerSeconds);
            notifyStartTimer((timerSeconds * 0.75).toFixed(0))
        }
    }, [session])

    React.useEffect(() => {
        getNewSession()
        getSectors()
    }, [])
    return (
        <>
            <UserSectorForm session={session} rootSectors={sectors}/>
        </>
    )
}

const mapStateToProps = state => ({
    session: state.session.session,
    sectors: state.sectors.sectors
});

export default connect(mapStateToProps, {
    getNewSession, getSectors
})(UserSectorPage);