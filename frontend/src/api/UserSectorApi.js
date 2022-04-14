import axios from "axios";
import {getJsonHeader} from "../helpers/api_helpers";


export const createUserSector = async (sessionId, userSector) => {
    const response = await axios.post(`/api/user_sectors/${sessionId}`, userSector, getJsonHeader());
    return response.data
}

export const updateUserSector = async (sessionId, userSector) => {
    const response = await axios.put(`/api/user_sectors/${sessionId}`, userSector, getJsonHeader());
    return response.data
}