import {alertActions} from "./alert.actions";
import {getRoots} from "../api/SectorsApi";
import {sectorsConstants} from "../constants/sectors.constants";

export const getSectorsSuccess = sectors => ({
    type: sectorsConstants.GET_SECTORS,
    sectors
});

export const getSectors = () => {
    return function (dispatch, getState) {
        if (!getState().sectors[0]) {
            return getRoots().then((sectors) => {
                dispatch(getSectorsSuccess(sectors))
            }).catch(error => {
                dispatch(alertActions.error('Can\'t get sectors ' + error));
            })
        } else {
            dispatch(getSectorsSuccess(getState.sectors))
        }
    }
};