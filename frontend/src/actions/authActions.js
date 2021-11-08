
import {
    USER_LOADED,
    USER_LOADING,
    AUTH_ERROR,
    LOGIN_SUCCESS,
    LOGIN_FAIL,
    LOGOUT_SUCCESS,
    REGISTER_SUCCESS,
    REGISTER_FAIL,
    UPDATE_FAIL,
    UPDATE_SUCCESS,
    PASSWORD_CHANGED
} from './types';
import axios from "axios";
import { returnErrors, clearErrors } from "./errorActions";
import Config from 'config';
// import bcrypt from "bcrypt";

export const login = (email, password) => (dispatch) => {
    // Headers
    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };

    // Request body
    const body = JSON.stringify({ email, password });
    axios.post(`${Config.apiURL}/accounts/authenticate`, body, config)
        .then(response => {
            dispatch(clearErrors());
            return dispatch({
                type: LOGIN_SUCCESS,
                payload: response.data
            })
        })
        .catch(err => {
            dispatch(
                returnErrors(err.response.data.message, err.response.status, 'LOGIN_FAIL')
            );
            dispatch({
                type: LOGIN_FAIL
            });
        });
}

export const forgotPassword = (email, password, dateOfBirth) => (dispatch, getState) => {

    const body = JSON.stringify({ email, password, dateOfBirth });

    //Headers
    const config = {
        headers: {
            "Content-Type": "application/json"
        }
    }
    axios.put(`${Config.apiURL}/accounts/`, body, config)
        .then(res => {
            dispatch(clearErrors());
            return dispatch({
                type: PASSWORD_CHANGED
            })
        })
        .catch(err => {
            dispatch(
                returnErrors(err.response.data.message, err.response.status, '')
            );
        })

}

export const createAccount = (email, password, dateOfBirth) => dispatch => {
    //Headers
    const config = {
        headers: {
            "Content-Type": "application/json"
        }
    }

    const body = JSON.stringify({ email, password, dateOfBirth });

    axios.post(`${Config.apiURL}/accounts/`, body, config)
        .then(res => {
            dispatch(clearErrors());
            return dispatch({
                type: REGISTER_SUCCESS,
                payload: res.data
            })
        })
        .catch(err => {
            console.log(err);
            dispatch(returnErrors(err.response.data.message, err.response.status, 'REGISTER_FAIL'))
            dispatch({
                type: REGISTER_FAIL
            })
        })
}




export const loginRequest = () => {
    // return {
    //     type: LOGIN_REQUEST
    // }
}

export const logoutUser = () => {
    return {
        type: LOGOUT_SUCCESS
    };
}

export const tokenConfig = getState => {
    //Get token from local storage
    const token = getState().auth.jwt;

    //Headers
    const config = {
        headers: {
            "Content-Type": "application/json"
        }
    }

    //If token, then add to headers
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }

    return config;
}

