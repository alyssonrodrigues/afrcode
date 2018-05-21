import React, { Component } from 'react'
import { withStyles } from 'material-ui/styles'
import { connect } from 'react-redux'
import Dialog, { DialogContent } from 'material-ui/Dialog'
import CircularProgress from 'material-ui/Progress/CircularProgress'

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
