import axios from "axios";
import {getJsonHeader} from "../helpers/api_helpers";

export const getRoots = async () => {
    const response = await axios.get(`/api/sectors`);
    return response.data
}

export const createSector = async (sector) => {
    const response = await axios.post(`/api/sectors/`, sector, getJsonHeader());
    return response.data
}

export const updateSector = async (sector) => {
    const response = await axios.put(`/api/sectors/${sector.id}`, sector, getJsonHeader());
    return response.data
}

export const deleteSector = async (id) => {
    const response = await axios.delete(`/api/sectors/${id}`);
    return response.data
}