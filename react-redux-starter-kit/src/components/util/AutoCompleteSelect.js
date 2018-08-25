import React from 'react'
import classNames from 'classnames'
import Select from 'react-select'
import NoSsr from '@material-ui/core/NoSsr'
import { withStyles } from '@material-ui/core/styles'
import Typography from '@material-ui/core/Typography'
import TextField from '@material-ui/core/TextField'
import Paper from '@material-ui/core/Paper'
import Chip from '@material-ui/core/Chip'
import MenuItem from '@material-ui/core/MenuItem'
import _ from 'lodash'

import styles from './AutoCompleteSelectStyles'

const CustomNoOptionsMessage = props => (
  <Typography
    color='textSecondary'
    className={props.selectProps.classes.noOptionsMessage}
    {...props.innerProps}>
    {props.children}
  </Typography>
)

const inputComponent = ({ inputRef, ...props }) => {
  return (<div ref={inputRef} {...props} />)
}

const CustomControl = props => (
  <TextField
    fullWidth
    InputProps={{
      inputComponent,
      inputProps: {
        className: props.selectProps.classes.input,
        inputRef: props.innerRef,
        children: props.children,
        ...props.innerProps
      }
    }}
    {...props.selectProps.textFieldProps} />
)

const CustomOption = props => (
  <MenuItem
    key={_.uniqueId('menu-item-')}
    buttonRef={props.innerRef}
    selected={props.isFocused}
    component='div'
    style={{
      fontWeight: props.isSelected ? 500 : 400
    }}
    {...props.innerProps}>
    {props.children}
  </MenuItem>
)

const CustomPlaceholder = props => (
  <Typography
    color='textSecondary'
    className={props.selectProps.classes.placeholder}
    {...props.innerProps}>
    {props.children}
  </Typography>
)

const CustomSingleValue = props => (
  <Typography className={props.selectProps.classes.singleValue} {...props.innerProps}>
    {props.children}
  </Typography>
)

const CustomValueContainer = props => (
  <div className={props.selectProps.classes.valueContainer}>{props.children}</div>
)

const CustomMultiValue = props => (
  <Chip
    tabIndex={-1}
    label={props.children}
    className={classNames(props.selectProps.classes.chip, {
      [props.selectProps.classes.chipFocused]: props.isFocused
    })}
    onDelete={event => {
      props.removeProps.onClick()
      props.removeProps.onMouseDown(event)
    }} />
)

const CustomMenu = props => (
  <Paper square className={props.selectProps.classes.paper} {...props.innerProps}>
    {props.children}
  </Paper>
)

const AutoCompleteSelect = props => {
  const {
    classes,
    theme,
    autoFocus,
    isMulti,
    label,
    placeholder,
    options,
    value,
    handleChange
  } = props
  const selectStyles = {
    input: base => ({
      ...base,
      color: theme.palette.text.primary
    })
  }
  return (
    <div className={classes.root}>
      <NoSsr>
        <Select
          classes={classes}
          styles={selectStyles}
          textFieldProps={{
            label,
            InputLabelProps: {
              shrink: true
            }
          }}
          options={options}
          components={{
            Option: CustomOption,
            Control: CustomControl,
            NoOptionsMessage: CustomNoOptionsMessage,
            Placeholder: CustomPlaceholder,
            SingleValue: CustomSingleValue,
            MultiValue: CustomMultiValue,
            ValueContainer: CustomValueContainer,
            Menu: CustomMenu
          }}
          value={value}
          onChange={handleChange}
          placeholder={placeholder}
          isMulti={isMulti}
          autoFocus={autoFocus} />
      </NoSsr>
    </div>
  )
}

export default withStyles(styles, { withTheme: true })(AutoCompleteSelect)
