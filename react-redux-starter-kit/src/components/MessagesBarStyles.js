import red from '@material-ui/core/colors/red'
import yellow from '@material-ui/core/colors/yellow'
import blue from '@material-ui/core/colors/blue'
import lightGreen from '@material-ui/core/colors/lightGreen'

export default theme => ({
  close: {
    width: theme.spacing.unit * 4,
    height: theme.spacing.unit * 4
  },
  error: {
    color: red[500]
  },
  info: {
    color: blue[400]
  },
  alert: {
    color: yellow[500]
  },
  success: {
    color: lightGreen['A400']
  },
  'default': {
    color: blue[400]
  }
})
