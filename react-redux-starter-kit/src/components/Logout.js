import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';

import { logoutUser } from '../actions/authenticationJwtActions';

class Logout extends Component {
  render() {
    console.log(this.props);
    this.props.logoutUser();
    return (<Redirect to='/' />);
  }

};

export default connect(null, { logoutUser })(Logout);