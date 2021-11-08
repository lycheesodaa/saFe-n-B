import PropTypes from 'prop-types';
// material
import { Box } from '@mui/material';
import logo from "../public/static/logo.png";

// ----------------------------------------------------------------------

Logo.propTypes = {
  sx: PropTypes.object
};

export default function Logo({ sx }) {
  return <Box component="img" src={logo} sx={{ width: 80, height: 80, ...sx }} />;
}
