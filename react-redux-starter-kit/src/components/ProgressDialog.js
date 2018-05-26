import React, { Component } from 'react'
import { withStyles } from '@material-ui/core/styles'
import { connect } from 'react-redux'
import Dialog from '@material-ui/core/Dialog'
import DialogContent from '@material-ui/core/DialogContent'
import CircularProgress from '@material-ui/core/CircularProgress'

import styles from './ProgressDialogStyles'

class ProgressDialog extends Component {
  render () {
    const { classes, operationProgress: { operationInProgress } } = this.props
    return (
      <Dialog PaperProps={{className: classes.paperClassName}}
        BackdropProps={{className: classes.backdropClassName}}
        open={operationInProgress}>
        <DialogContent>
          <CircularProgress />
        </DialogContent>
      </Dialog>
    )
  }
}

const materialUIEnhanced = withStyles(styles)(ProgressDialog)

const mapStateToProps = ({ operationProgress }) => ({ operationProgress })

export default connect(mapStateToProps)(materialUIEnhanced)
