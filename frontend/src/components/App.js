import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Header from "./Header/Header";
import Regulations from "./Regulations/Regulations";
import Statistics from "./Statistics/Statistics";
import Scheduler from "./Scheduler/Scheduler";
import Blog from "./Blog/Blog";
import Forum from "./Forum/Forum";

function App() {
  return (
    <Router>
      <Header />
      <Switch>
        <Route path="/f&b-regulations" exact component={Regulations} />
        <Route path="/covid-19-statistics" exact component={Statistics} />
        <Route path="/employees" exact component={Employee} />
        <Route path="/scheduler" exact component={Scheduler} />
        <Route path="/blog" exact component={Blog} />
        <Route path="/forum" exact component={Forum} />
      </Switch>
    </Router>
  );
}

export default App;
