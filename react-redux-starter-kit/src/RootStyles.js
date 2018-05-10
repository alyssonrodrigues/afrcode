import blue from 'material-ui/colors/blue'
import { createMuiTheme } from 'material-ui/styles/index'

export const theme = createMuiTheme({
  palette: {
    primary: blue
  }
})

const styles = theme => ({
  root: theme.typography.body1
})

export default styles
