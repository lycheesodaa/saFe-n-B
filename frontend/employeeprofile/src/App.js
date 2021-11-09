import React from 'react';
// import logo from './logo.svg';
import './App.css';
import Header from './Components/header'
import Navbar from './Components/Navbar'
import EmployeeDetails from './Components/EmployeeDetails'
import Records from './Components/Records'
import Covid19History from './Components/Covid-19History'
import EmergencyContact from './Components/EmergencyContact'



function App() {
  return (
    <div className="App">
      <Header />
      <hr />
      <Navbar />
      <hr />
      <EmployeeDetails />
      <hr />
      <Records />
      <hr />
      <Covid19History />
      <hr />
      <EmergencyContact />

    </div>
  );
}

export default App;
