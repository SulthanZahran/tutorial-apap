import React from "react";
import classes from "./style.module.css";

const Pagination = ({total, perPage, paginate}) => {
    const pageNumbers = [];
    for (let i=1; i<= Math.ceil(total / perPage); i++){
        pageNumbers.push(i);
    }

    return (
        <nav aria-label="Page navigation example" className={classes.nav}>
            <ul className="pagination">
                {pageNumbers.map(number => (
                    <li className={classes.li}>
                        <a className="page-link" onClick={() => paginate(number)} href="!#">{number}</a>
                    </li>
                ))}
            </ul>
        </nav>
    )
}

export default Pagination;