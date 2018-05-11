import red from 'material-ui/colors/red'
import yellow from 'material-ui/colors/yellow'
import blue from 'material-ui/colors/blue'
import green from 'material-ui/colors/green'

const styles = theme => ({
  close: {
    width: theme.spacing.unit * 4,
    height: theme.spacing.unit * 4
  },
  error: {
    '& span': {
      color: red[500]
    }
  },
  info: {
    '& span': {
      color: blue[400]
    }
  },
  alert: {
    '& span': {
      color: yellow[500]
    }
  },
  success: {
    '& span': {
      color: green[500]
    }
  },
  'default': {
    '& span': {
      color: blue[400]
    }
  }
})

export default styles
