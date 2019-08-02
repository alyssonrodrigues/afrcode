import React from 'react'
import { withStyles } from '@material-ui/core/styles'
import Snackbar from '@material-ui/core/Snackbar'
import IconButton from '@material-ui/core/IconButton'
import CloseIcon from '@material-ui/icons/Close'
import { connect } from 'react-redux'
import red from '@material-ui/core/colors/red'
import blue from '@material-ui/core/colors/blue'
import orange from '@material-ui/core/colors/orange'
import green from '@material-ui/core/colors/green'

import styles from './MessagesBarStyles'
import { dismissMessage } from '../actions/messagesActions'

const backgroundColors = {
  error: red['A700'],
  info: blue[500],
  alert: orange[900],
  success: green[500],
  'default': blue[400],
  'none': 'rgba(0, 0, 0, 0)'
}

export const MessagesBar = props => {
  const { classes } = props
  const { message } = props.messages
  const open = Boolean(message)
  const backgroundColor = open ? backgroundColors[message.type] || backgroundColors['default'] : backgroundColors['none']
  const msgText = open ? message.msgText : null
  return (
    <Snackbar
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'center'
      }}
      ContentProps={{style: { backgroundColor, color: '#fff', flexWrap: 'noWrap' }, elevation: open ? 6 : 0}}
      open={open}
      autoHideDuration={6000}
      onClose={props.dismissMessage}
      message={<span id='message-id'>{msgText}</span>}
      action={[
        <IconButton
          key='close'
          aria-label='Close'
          color='inherit'
          style={{padding: 8}}
          onClick={props.dismissMessage}>
          <CloseIcon className={classes.close} />
        </IconButton>
      ]}
    />
  )
}

const materialUIEnhance = withStyles(styles)(MessagesBar)

const mapStateToProps = ({ messages }) => ({ messages })

export default connect(mapStateToProps, { dismissMessage })(materialUIEnhance)
