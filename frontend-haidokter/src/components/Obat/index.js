import React from "react";
import classes from "./styles.module.css";

const Obat = (props) => {
    const { nama } = props;
    return (
        <div className={classes.Obat}>
            <p>{nama}</p>
        </div>
    );
};
export default Obat;