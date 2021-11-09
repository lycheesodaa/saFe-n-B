import { GET_EMPLOYEE, ADD_ART, ADD_TEMPERATURE } from "./types";
import axios from "axios";
import Config from 'config';

export const getEmployee = (email) => (dispatch) => {
    axios
        .get(`${Config.apiUrl}/employees/${email}`)
        .then(res =>
            dispatch({
                type: GET_EMPLOYEE,
                payload: res.data
            })
        )
}

export const addArt = (email, dateTaken, result) => (dispatch) => {
    // Headers
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };

    // Request body
    const body = JSON.stringify({ dateTaken, result });
    axios
        .post(`${Config.apiUrl}/employees/${email}/art`, body, config)
        .then(res =>
            dispatch({
                type: ADD_ART,
                payload: res.data
            })
        )
}

export const addTemperature = (email, dateTaken, record) => (dispatch) => {
    // Headers
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };

    // Request body
    const body = JSON.stringify({ dateTaken, record });
    axios
        .post(`${Config.apiUrl}/employees/${email}/temperature`, body, config)
        .then(res =>
            dispatch({
                type: ADD_TEMPERATURE,
                payload: res.data
            })
        )
}