import {sessionConstants} from "../constants/session.constants";

export const session = (state = {}, action) => {
    switch (action.type) {
        case sessionConstants.GET_NEW_SESSION:
            return {
                ...state,
                session: action.session
            }
        default:
            return state
    }
}