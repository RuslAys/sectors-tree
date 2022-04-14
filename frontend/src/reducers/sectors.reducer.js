import {sectorsConstants} from "../constants/sectors.constants";

export const sectors = (state = {}, action) => {
    switch (action.type) {
        case sectorsConstants.GET_SECTORS:
            return {
                ...state,
                sectors: action.sectors
            }
        default:
            return state
    }
}