<<<<<<< HEAD
import { combineReducers } from "redux";
import authReducer from "./authReducer";
import errorReducer from "./errorReducer";
import scrapeReducer from "./scrapeReducer";
import firmReducer from "./firmReducer";

export default combineReducers({
    auth: authReducer,
    error: errorReducer,
    scrape: scrapeReducer,
    firm: firmReducer
=======
import { combineReducers } from "redux";
import authReducer from "./authReducer";
import errorReducer from "./errorReducer";
import scrapeReducer from "./scrapeReducer";

export default combineReducers({
    auth: authReducer,
    error: errorReducer,
    scrape: scrapeReducer
>>>>>>> 5edcc10ffe0cb908ab82af6dae873676e6872857
})