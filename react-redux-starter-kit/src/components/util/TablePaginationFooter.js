import React from 'react'
import TablePagination from '@material-ui/core/TablePagination'
import TableRow from '@material-ui/core/TableRow'
import TableFooter from '@material-ui/core/TableFooter'

const TablePaginationFooter = ({ count, page, initialRowsPerPage, rowsPerPage, handleChangePage, handleChangeRowsPerPage }) => (
  <TableFooter>
    <TableRow>
      <TablePagination count={count} page={page} rowsPerPage={rowsPerPage}
        labelRowsPerPage='Itens por página'
        labelDisplayedRows={({from, to, count}) => `${from}-${to} de ${count}`}
        rowsPerPageOptions={[initialRowsPerPage, initialRowsPerPage * 2, initialRowsPerPage * 3]}
        onChangePage={handleChangePage}
        onChangeRowsPerPage={handleChangeRowsPerPage} />
    </TableRow>
  </TableFooter>
)

export default TablePaginationFooter
