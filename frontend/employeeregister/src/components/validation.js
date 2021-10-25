const validation = (values) =>{
    let errors = {};
    if(!values.fullname){
        errors.fullname="Name is required"
    }
    if(!values.email){
        errors.email="email is required"
    }
    else if(!/\S+@\S+\.\S+/.test(values.email)){
        errors.email = "email is invalid"
    }
    if(!values.password){
        errors.password="password is required"
    }
    if(!values.date){
        errors.date="Date is required"
    }
    if(!values.NRIC){
        errors.NRIC="NRIC is required"
    }
    else if(values.NRIC.length !== 9){
        errors.NRIC = "Invalid NRIC"
    }
    if(!values.contactno){
        errors.contactno="Contact number is required"
    }
    else if(values.contactno.length != 8){
        errors.contactno= "Invalid number"
    }
    if(!values.address){
        errors.address="Address is required"
    }
    return(
        errors
    )
}
export default validation