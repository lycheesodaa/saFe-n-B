import React, { useState, useEffect } from "react";
import validation from "./validation";

const SignupForm = ({submitForm}) => {
  const [values, setValues] = useState({
    fullname: "",
    password: "",
    email: "",
    date: "",
    NRIC: "",
    contactno: "",
    address: "",
  });

  const [errors, setErrors] = useState({});
  const [dataIsCorrect, setDataIsCorrect] = useState(false);

  const handleChange = (event) => {
    setValues({
      ...values,
      [event.target.name]: event.target.value,
    });
  };
  const handleFormSubmit = (event) => {
    event.preventDefault();
    setErrors(validation(values));
    setDataIsCorrect(true);
  };

  useEffect(() => {
    if (Object.keys(errors).length === 0 && dataIsCorrect) {
      submitForm(true);
    }
  }, [errors]);
  return (
    <div className="container">
      <div className="app-wrapper">
        <div>
          <h2 className="title">Create Employee Account</h2>
        </div>
        <form className="form-wrapper">
          <div className="name">
            <label className="label">full name</label>
            <input
              className="input"
              type="text"
              name="fullname"
              value={values.fullname}
              onChange={handleChange}
            />
            {errors.fullname && <p className="error">{errors.fullname}</p>}
          </div>
          <div className="password">
            <label className="label">password</label>
            <input
              className="input"
              type="password"
              name="password"
              value={values.password}
              onChange={handleChange}
            />
            {errors.password && <p className="error">{errors.password}</p>}
          </div>
          <div className="email">
            <label className="label">email</label>
            <input
              className="input"
              type="email"
              name="email"
              values={values.email}
              onChange={handleChange}
            />
            {errors.email && <p className="error">{errors.email}</p>}
          </div>
          <div className="dateofbirth">
            <label className="label">Date of Birth</label>
            <input
              className="input"
              type="date"
              name="date"
              value={values.date}
              onChange={handleChange}
            />
            {errors.date && <p className="error">{errors.date}</p>}
          </div>
          <div className="nric">
            <label className="label">NRIC</label>
            <input
              className="input"
              type="name"
              name="NRIC"
              value={values.NRIC}
              onChange={handleChange}
            />
            {errors.NRIC && <p className="error">{errors.NRIC}</p>}
          </div>
          <div className="contactNo">
            <label className="label">Contact Number</label>
            <input
              className="input"
              type="name"
              name="contactno"
              value={values.contactno}
              onChange={handleChange}
            />
            {errors.contactno && <p className="error">{errors.contactno}</p>}
          </div>
          <div className="address">
            <label className="label">Address</label>
            <input
              className="input"
              type="name"
              name="address"
              value={values.address}
              onChange={handleChange}
            />
            {errors.address && <p className="error">{errors.address}</p>}
          </div>
          <div>
            <button className="submit" onClick={handleFormSubmit}>
              sign up
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default SignupForm;
