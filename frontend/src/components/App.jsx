import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Login from "./Login/Login";
import Register from "./Register/Register";
import StartTradeBorrower from "./StartTradeBorrower/StartTradeBorrower";
import StartTradeLender from "./StartTradeLender/StartTradeLender";
import MyTradesBorrower from "./MyTradesBorrower/MyTradesBorrower";
import MyTradesLender from "./MyTradesLender/MyTradesLender";
import MyProfileBorrower from "./MyProfileBorrower/MyProfileBorrower";
import MyProfileLender from "./MyProfileLender/MyProfileLender";
import Home from "./Home/Home";


function App() {
    return (
        <Router>
            <Switch>
                <Route path='/' exact component={Login} />
                <Route path='/home' exact component={Login} />
                <Route path='/login' exact component={Login} />
                <Route path='/register' exact component={Register} />
                <Route path='/start-trade-borrower' exact component={StartTradeBorrower} />
                <Route path='/start-trade-lender' exact component={StartTradeLender} />
                <Route path='/my-trades-borrower' exact component={MyTradesBorrower} />
                <Route path='/my-trades-lender' exact component={MyTradesLender} />
                <Route path='/my-profile-borrower' exact component={MyProfileBorrower} />
                <Route path='/my-profile-lender' exact component={MyProfileLender} />
            </Switch>
        </Router>
    );

}

export default App;
