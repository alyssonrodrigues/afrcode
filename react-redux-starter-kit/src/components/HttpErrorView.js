import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { withStyles } from '@material-ui/core/styles'
import Dialog from '@material-ui/core/Dialog'
import DialogTitle from '@material-ui/core/DialogTitle'
import DialogContent from '@material-ui/core/DialogContent'
import DialogContenText from '@material-ui/core/DialogContentText'
import DialogActions from '@material-ui/core/DialogActions'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'

import { dismissMessage } from '../actions/messagesActions'

const styles = {
  paperClassName: {
    borderRadius: '2%',
    outline: 'none'
  },
  backdropClassName: {
    backgroundColor: 'rgba(255, 255, 255, 0.65)'
  },
  item: {
    paddingTop: 12
  },
  link: {
    padding: 12
  }
}

export class HttpErrorView extends Component {
  componentWillUnmount () {
    this.props.dismissMessage()
  }

  render () {
    const { classes, messages: { message }, history } = this.props
    return (
      <main>
        <Dialog open
          BackdropProps={{className: classes.backdropClassName}}
          PaperProps={{className: classes.paperClassName, elevation: 2}}>
          <DialogTitle disableTypography>
            <Typography variant='display1' color='error'>
              Ocorreu um erro inesperado
            </Typography>
          </DialogTitle>
          <DialogContent>
            <DialogContenText component='div'>
              <Typography variant='title'>
                Aguarde alguns instantes e tente novamente.
              </Typography>
            </DialogContenText>
            <DialogContenText component='div'>
              <Typography variant='subheading' className={classes.item}>
                Entre em contato através do 0800 644 1500 se o problema persistir.
              </Typography>
            </DialogContenText>
            {message ? (
              <DialogContenText component='div'>
                <Typography variant='body1' className={classes.item} style={{ maxWidth: 800 }}>
                  Mensagem: {`"${message.msgText}"`}
                </Typography>
              </DialogContenText>
            ) : null}
          </DialogContent>
          <DialogActions>
            <Button className={classes.link} onClick={() => history.push('/')} color='primary'>
              Voltar
            </Button>
          </DialogActions>
        </Dialog>
      </main>
    )
  }
}

const materialUIEnhanced = withStyles(styles)(HttpErrorView)

const mapStateToProps = ({ messages }) => ({ messages })

const reduxEnhanced = connect(mapStateToProps, { dismissMessage })(materialUIEnhanced)

export default withRouter(reduxEnhanced)
