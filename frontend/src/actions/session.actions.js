import {getNew} from "../api/SessionApi";
import {sessionConstants} from "../constants/session.constants";
import {alertActions} from "./alert.actions";

export const getNewSessionSuccess = session => ({
    type: sessionConstants.GET_NEW_SESSION,
    session
});

export const getNewSession = () => {
    return function (dispatch) {
        return getNew().then((session) => {
            dispatch(getNewSessionSuccess(session))
        }).catch(error => {
            dispatch(alertActions.error('Can\'t get new session ' + error));
        })
    }
};