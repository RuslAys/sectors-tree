import {combineReducers} from 'redux';
import {alert} from "./alert.reducer";
import {session} from "./session.reducer"
import {sectors} from "./sectors.reducer";

const rootReducer = combineReducers({
    alert,
    session,
    sectors
});

export default rootReducer;