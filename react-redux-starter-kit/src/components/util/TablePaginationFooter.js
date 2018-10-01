import React from 'react'
import TablePagination from '@material-ui/core/TablePagination'
import TableRow from '@material-ui/core/TableRow'
import TableCell from '@material-ui/core/TableCell'
import TableFooter from '@material-ui/core/TableFooter'

const TablePaginationFooter = ({
  count,
  page,
  initialRowsPerPage,
  rowsPerPage,
  handleChangePage,
  handleChangeRowsPerPage,
  component: Component,
  ...rest
}) => (
  <TableFooter>
    <TableRow>
      <TablePagination
        component={Component || TableCell}
        count={count} page={page} rowsPerPage={rowsPerPage}
        labelRowsPerPage='Itens por página'
        labelDisplayedRows={({from, to, count}) => `${from}-${to} de ${count}`}
        rowsPerPageOptions={[initialRowsPerPage, initialRowsPerPage * 2, initialRowsPerPage * 3]}
        onChangePage={handleChangePage}
        onChangeRowsPerPage={handleChangeRowsPerPage}
        {...rest} />
    </TableRow>
  </TableFooter>
)

export default TablePaginationFooter
