import React, { Component } from 'react'
import './css/Main.css'


class Navbar extends Component {
    render() {
        return (
            <nav className="navbar">
                <a href="EmployeeDetails"><i className="fa fa-fw fa-user"></i>Details</a>
                <a href="Records"><i className="fa fa-fw fa-xing"></i>Records</a>
                <a id="Covid19History" href="Covid19History"><i className="fa fa-github-alt"></i>COVID-19</a> 
                <a href="EmergencyContact"><i className="fa fa-fw fa-envelope"></i>Emergency Contact</a>
            </nav>
        )
    }
}

export default Navbar
