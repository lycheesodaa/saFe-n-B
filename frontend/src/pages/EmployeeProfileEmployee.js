import { filter } from 'lodash';
import { Icon } from '@iconify/react';
import { sentenceCase } from 'change-case';
import { useEffect, useState, useCallback } from 'react';
import plusFill from '@iconify/icons-eva/plus-fill';
import { Link as RouterLink } from 'react-router-dom';
import {
    Modal,
    ModalHeader,
    ModalBody,
    Form,
    FormGroup,
    Label,
    Input,
} from 'reactstrap';
// material
import { ThemeProvider, createTheme } from '@material-ui/core/styles';
import {
    Card,
    Table,
    Stack,
    Avatar,
    Button,
    Checkbox,
    TableRow,
    TableBody,
    TableCell,
    Container,
    Typography,
    TableContainer,
    TablePagination,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    TextField,
    MenuItem
} from '@mui/material';
// components
import Page from '../components/Page';
import ARTTable from '../components/ARTTable';
import TempTable from '../components/TempTable';
//
import { connect } from 'react-redux';
import { getEmployeesByFirmEmail } from "../actions/firmActions";
import { useNavigate, useParams } from 'react-router-dom';
import { getEmployee, addArt, addTemperature } from '../actions/employeeActions';
import moment from "moment";
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DesktopDatePicker from '@mui/lab/DesktopDatePicker';
import { setDate } from 'date-fns';



// ----------------------------------------------------------------------


function EmployeeProfileEmployee({ employee, getEmployee, addArt, addTemperature, user }) {
    const [modalART, setModalART] = useState(false);
    const [modalTemp, setModalTemp] = useState(false);
    const [thisEmployee, setThisEmployee] = useState({
        "address": "Room No. 707, YMCA,",
        "artList": [],
        "contact": "2193821093",
        "dateOfBirth": "02/11/2021",
        "email": "employee1@email.com",
        "name": "employee 1",
        "nric": "dsnj3",
        "password": "dGVzdDEyMw==",
        "tempList": [],
        "vacinnates": false,
    });
    useEffect(() => {
        getEmployee(user.username);
    }, [])
    useEffect(() => {
        if (Object.keys(employee).length > 0) {
            setThisEmployee(employee);
        }
    }, [employee])
    const colortheme = createTheme({
        palette: {
            primary: { main: "#e91e63", contrastText: "#000" },
            secondary: { main: "#c8facd", contrastText: "#000" }
        }
    });
    const [open, setOpen] = useState(false);
    const [result, setResult] = useState(false);
    const [record, setRecord] = useState(0.0);
    const [dateART, setDateART] = useState(null);
    const [dateTemp, setDateTemp] = useState(null);
    const handleChangeDateART = (e) => setDateART(e.target.value);
    const handleChangeDateTemp = (e) => setDateTemp(e.target.value);
    const handleChangeResult = (e) => {
        console.log(e.target.value);
        setResult(e.target.value)
    };
    const handleChangeRecord = (e) => {
        console.log(e.target.value);
        setRecord(e.target.value)
    };
    const handleToggleART = () => {
        setModalART(!modalART);
    };
    const handleToggleTemp = () => {
        setModalTemp(!modalTemp);
    };

    const handleOnSubmitART = () => {
        console.log(dateART);
        console.log(result);
        const [year, month, day] = dateART.split("-");
        var newDateART = `${day}/${month}/${year}`
        addArt(user.username, newDateART, result);
        if (modalART) {
            handleToggleART();
        }
    };
    const handleOnSubmitTemp = () => {
        console.log(dateTemp);
        console.log(record);
        const [year, month, day] = dateTemp.split("-");
        var newDateTemp = `${day}/${month}/${year}`
        addTemperature(user.username, newDateTemp, record);
        if (modalTemp) {
            handleToggleTemp();
        }
    };


    return (
        <Page title="Employee Dashboard">
            <ThemeProvider theme={colortheme}>
                <Container>
                    <Typography variant="h1" sx={{ mb: 2 }} color="secondary">
                        Employee Profile
                    </Typography>
                    <Typography variant="h4" sx={{ mb: 2 }} color="secondary">
                        Basic Details
                    </Typography>
                    <Card sx={{ p: 5 }}>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Name: {thisEmployee.name}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Email: {thisEmployee.email}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Contact: {thisEmployee.contact}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Date Of Birth: {thisEmployee.dateOfBirth}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            NRIC/FIN: {thisEmployee.nric}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Address: {thisEmployee.address}
                        </Typography>
                    </Card>
                </Container>
                <Container>
                    <Typography variant="h4" sx={{ mb: 2, mt: 2 }} color="secondary">
                        ART History
                    </Typography>
                    <ARTTable artList={thisEmployee.artList} />
                    <Button
                        variant="contained"
                        startIcon={<Icon icon={plusFill} />}
                        sx={{ mb: 2, mt: 2 }}
                        onClick={handleToggleART}
                    >
                        Add ART
                    </Button>
                    <Modal isOpen={modalART} toggle={handleToggleART}>
                        <ModalHeader toggle={handleToggleART}>Add ART </ModalHeader>
                        <ModalBody>
                            <Form>
                                <FormGroup>
                                    <Label for="dateART">Date Taken</Label>
                                    <Input
                                        type="date"
                                        name="dateART"
                                        id="dateART"
                                        className="mb-3"
                                        onChange={handleChangeDateART}
                                    />
                                    <Label for="result">Result</Label>
                                    <Input
                                        type="select"
                                        name="result"
                                        id="result"
                                        className="mb-3"
                                        onChange={handleChangeResult}
                                    >
                                        <option value={false}>
                                            Negative
                                        </option>
                                        <option value={true}>
                                            Positive
                                        </option>

                                    </Input>
                                    <Button
                                        onClick={handleOnSubmitART}
                                    >
                                        Add ART
                                    </Button>
                                </FormGroup>
                            </Form>
                        </ModalBody>
                    </Modal>
                </Container>
                <Container>
                    <Typography variant="h4" sx={{ mb: 2, mt: 2 }} color="secondary">
                        Temperature History
                    </Typography>
                    <TempTable tempList={thisEmployee.tempList} />
                    <Button
                        variant="contained"
                        startIcon={<Icon icon={plusFill} />}
                        sx={{ mb: 2, mt: 2 }}
                        onClick={handleToggleTemp}
                    >
                        Add Temperature
                    </Button>
                    <Modal isOpen={modalTemp} toggle={handleToggleTemp}>
                        <ModalHeader toggle={handleToggleTemp}>Add Temperature </ModalHeader>
                        <ModalBody>
                            <Form>
                                <FormGroup>
                                    <Label for="dateTemp">Date Taken</Label>
                                    <Input
                                        type="date"
                                        name="dateTemp"
                                        id="dateTemp"
                                        className="mb-3"
                                        onChange={handleChangeDateTemp}
                                    />
                                    <Label for="record">Record</Label>
                                    <Input
                                        type="num"
                                        step="0.01"
                                        name="record"
                                        id="record"
                                        className="mb-3"
                                        onChange={handleChangeRecord}
                                    />
                                    <Button
                                        onClick={handleOnSubmitTemp}
                                    >
                                        Add Temperature
                                    </Button>
                                </FormGroup>
                            </Form>
                        </ModalBody>
                    </Modal>
                </Container>
            </ThemeProvider>
        </Page>
    );
}

const mapStateToProps = (state) => ({
    employee: state.singleEmployee.employee,
    user: state.auth.user
})

export default connect(mapStateToProps, { getEmployee, addArt, addTemperature })(EmployeeProfileEmployee);
