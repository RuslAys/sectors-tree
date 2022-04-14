import axios from "axios";

export const getNew = async () => {
    const response = await axios.get(`/api/sessions/new`);
    return response.data
}