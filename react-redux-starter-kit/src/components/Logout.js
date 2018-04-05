import React from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';

import { logoutUser } from '../actions/authenticationJwtActions';

const Logout = (props) => {
  props.logoutUser();
  return (<Redirect to='/' />);
};

export default connect(null, { logoutUser })(Logout);