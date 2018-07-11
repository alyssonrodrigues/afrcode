import React from 'react'
import { withStyles } from '@material-ui/core/styles'
import Snackbar from '@material-ui/core/Snackbar'
import IconButton from '@material-ui/core/IconButton'
import CloseIcon from '@material-ui/icons/Close'
import { connect } from 'react-redux'

import styles from './MessagesBarStyles'
import { dismissMessage } from '../actions/messagesActions'

export const MessagesBar = props => {
  const { classes } = props
  const { message } = props.messages
  const open = Boolean(message)
  const className = open ? classes[message.type] : null
  const msgText = open ? message.msgText : null
  return (
    <Snackbar
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'center'
      }}
      open={open}
      autoHideDuration={6000}
      onClose={props.dismissMessage}
      message={<span id='message-id' className={className}>{msgText}</span>}
      action={[
        <IconButton
          key='close'
          aria-label='Close'
          color='inherit'
          className={classes.close}
          onClick={props.dismissMessage}>
          <CloseIcon />
        </IconButton>
      ]}
    />
  )
}

const materialUIEnhance = withStyles(styles)(MessagesBar)

const mapStateToProps = ({ messages }) => ({ messages })

export default connect(mapStateToProps, { dismissMessage })(materialUIEnhance)
