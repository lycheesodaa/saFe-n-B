import { GET_EMPLOYEE, ADD_ART, ADD_TEMPERATURE } from "../actions/types";

const initialState = {
    employee: {}
}

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_EMPLOYEE:
            return {
                ...state,
                employee: action.payload
            };
        case ADD_ART:
            return {
                ...state,
                employee: action.payload
            };
        case ADD_TEMPERATURE:
            return {
                ...state,
                employee: action.payload
            }
        default:
            return state;
    }
}