import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';

import { userActions } from '../_actions';

function RegisterPage() {
    const [user, setUser] = useState({
        nameofEstablishment: '',
        contactNumber: '',
        email: '',
        password: ''
    });
    const [submitted, setSubmitted] = useState(false);
    const registering = useSelector(state => state.registration.registering);
    const dispatch = useDispatch();

    // reset login status
    useEffect(() => {
        dispatch(userActions.logout());
    }, []);

    function handleChange(e) {
        const { name, value } = e.target;
        setUser(user => ({ ...user, [name]: value }));
    }

    function handleSubmit(e) {
        e.preventDefault();

        setSubmitted(true);
        if (user.nameofEstablishment && user.contactNumber && user.email && user.password) {
            dispatch(userActions.register(user));
        }
    }

    return (
        <div className="col-lg-8 offset-lg-2">
            <h2>Register</h2>
            <form name="form" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Name of Establishment</label>
                    <input type="text" name="nameofEstablishment" value={user.nameofEstablishment} onChange={handleChange} className={'form-control' + (submitted && !user.nameofEstablishment ? ' is-invalid' : '')} />
                    {submitted && !user.nameofEstablishment &&
                        <div className="invalid-feedback">Name of Establishment is required</div>
                    }
                </div>
                <div className="form-group">
                    <label>Contact Number</label>
                    <input type="number" name="contactNumber" value={user.contactNumber} onChange={handleChange} className={'form-control' + (submitted && !user.contactNumber ? ' is-invalid' : '')} />
                    {submitted && !user.contactNumber &&
                        <div className="invalid-feedback">Contact Number is required</div>
                    }
                </div>
                <div className="form-group">
                    <label>Email</label>
                    <input type="email" name="email" value={user.email} onChange={handleChange} className={'form-control' + (submitted && !user.email ? ' is-invalid' : '')} />
                    {submitted && !user.email &&
                        <div className="invalid-feedback">Email is required</div>
                    }
                </div>  
                <div className="form-group">
                    <label>Password</label>
                    <input type="password" name="password" value={user.password} onChange={handleChange} className={'form-control' + (submitted && !user.password ? ' is-invalid' : '')} />
                    {submitted && !user.password &&
                        <div className="invalid-feedback">Password is required</div>
                    }
                </div>
                <div className="form-group">
                    <button className="btn btn-primary">
                        {registering && <span className="spinner-border spinner-border-sm mr-1"></span>}
                        Register
                    </button>
                    <Link to="/login" className="btn btn-link">Cancel</Link>
                </div>
            </form>
        </div>
    );
}

export { RegisterPage };