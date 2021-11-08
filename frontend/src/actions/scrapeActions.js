import {GET_SCRAPED_DATA} from './types';
import axios from "axios";
import Config from 'config';

export const getScrapedData = () => dispatch => {

    axios
        .get(`${Config.apiUrl}/covid`)
        .then(res => {
            console.log(res.data);
            return dispatch({
                type: GET_SCRAPED_DATA,
                payload: res.data
            })
        }
            
        )
}