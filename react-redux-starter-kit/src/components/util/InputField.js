import React from 'react'
import { Field } from 'react-final-form'
import Typography from '@material-ui/core/Typography'

const InputField = ({ component: Component, fieldProps, ...componentProps }) => (
  <Field {...fieldProps}>
    {({ input, meta }) => (
      <React.Fragment>
        <Component {...input} {...componentProps} />
        {meta.error && meta.touched && (
          <Typography variant='subtitle2' color='error'>{meta.error}</Typography>
        )}
      </React.Fragment>
    )}
  </Field>
)

export default InputField
