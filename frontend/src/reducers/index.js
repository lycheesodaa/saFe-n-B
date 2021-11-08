import { combineReducers } from "redux";
import authReducer from "./authReducer";
import errorReducer from "./errorReducer";
import scrapeReducer from "./scrapeReducer";

export default combineReducers({
    auth: authReducer,
    error: errorReducer,
    scrape: scrapeReducer
})