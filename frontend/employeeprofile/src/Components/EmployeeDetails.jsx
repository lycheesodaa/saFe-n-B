import React, { Component } from 'react'
import biologo from './public/image/biopicimg.jpg'

class EmployeeDetails extends Component {
    render() {
        return (
            <section id="EmployeeDetails" className="EmployeeDetails">
                    <h1>NAME OF EMPLOYEE HERE</h1> 

                    <img src={biologo} width="180" height="180" alt="abtimg"/>
                    <p>Name: </p>
                    <p>Contact: </p>
                    <p>Email: </p>
                    <p>Address: </p>
                    <p>DOB: </p>
                    <p>Gender: </p>
                    <p>Nationality: </p>
                
            </section>
        )
    }
}

export default EmployeeDetails
