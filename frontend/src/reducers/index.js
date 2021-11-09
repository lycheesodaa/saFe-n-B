import { combineReducers } from "redux";
import authReducer from "./authReducer";
import errorReducer from "./errorReducer";
import scrapeReducer from "./scrapeReducer";
import firmReducer from "./firmReducer";
import employeeReducer from "./employeeReducer";

export default combineReducers({
    auth: authReducer,
    error: errorReducer,
    scrape: scrapeReducer,
    firm: firmReducer,
    singleEmployee: employeeReducer
})