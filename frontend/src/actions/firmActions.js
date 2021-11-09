import { GET_EMPLOYEES } from "./types";
import axios from "axios";
import Config from 'config';

export const getEmployeesByFirmEmail = (email) => (dispatch) => {
    axios
    .get(`${Config.apiUrl}/employees/firm/${email}`)
    .then(res =>
        dispatch({
            type: GET_EMPLOYEES,
            payload: res.data
        })
    )
}