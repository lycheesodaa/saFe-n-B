import {GET_SCRAPED_DATA } from "../actions/types";

const initialState = {
    scrapedData: {}
}

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_SCRAPED_DATA:
            return {
                ...state,
                scrapedData: action.payload
            };
        default:
            return state;
    }
}