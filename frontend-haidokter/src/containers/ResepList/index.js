import React, { Component } from "react";
import Resep from "../../components/Resep";
import Button from "../../components/Button";
import Modal from "../../components/Modal";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig"
import Pagination from "../../components/Pagination/Pagination";

class ResepList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            reseps: [],
            isLoading: false,
            isCreate: false,
            isEdit: false,
            namaDokter: "",
            namaPasien: "",
            catatan: "",
            currPage:1,
            perPage:5

        };
        this.handleClickLoading = this.handleClickLoading.bind(this);
        this.handleAddResep = this.handleAddResep.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.handleChangeField = this.handleChangeField.bind(this);
        this.handleSubmitAddResep = this.handleSubmitAddResep.bind(this);
        this.handleEditResep = this.handleEditResep.bind(this);
        this.handleSubmitEditResep = this.handleSubmitEditResep.bind(this);
        this.handleDeleteResep = this.handleDeleteResep.bind(this);
        this.handleInputChange  = this.handleInputChange .bind(this);
    }

    async handleDeleteResep(noResep) {
        try {
            await APIConfig.delete(`/resep/${noResep}`);
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async handleSubmitEditResep(event) {
        event.preventDefault();
        try {
            const data = {
                namaDokter: this.state.namaDokter,
                namaPasien: this.state.namaPasien,
                catatan: this.state.catatan,
            };
            await APIConfig.put(`/resep/${this.state.noResep}`, data);
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }

    handleEditResep(resep) {
        this.setState({
            isEdit: true,
            noResep: resep.noResep,
            namaDokter: resep.namaDokter,
            namaPasien: resep.namaPasien,
            catatan: resep.catatan,
        });
    }

    handleAddResep() {
        this.setState({ isCreate: true });
    }
    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate: false, isEdit: false, namaDokter: "", namaPasien: "", catatan: "",});
    }
    handleChangeField(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    async handleSubmitAddResep(event) {
        event.preventDefault();
        try {
            const data = {
                namaDokter: this.state.namaDokter,
                namaPasien: this.state.namaPasien,
                catatan: this.state.catatan,
            };
            await APIConfig.post("/resep", data);
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }

    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({ isLoading: !currentLoading });
        console.log(this.state.isLoading);
    }

    componentDidMount() {
        this.loadData();
    }

    async loadData() {
        try {
            const {data} = await APIConfig.get("/reseps");
            this.setState({ reseps: data });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    handleInputChange = event => {
        const dokter = event.target.value;
        this.inputChange(dokter);
    }

    async inputChange(dokter){
        const data = [];
        const response = await APIConfig.get("/reseps");
        for (let key in response.data){
            if (response.data[key].namaDokter.toLowerCase().includes(dokter.toLowerCase())){
                data.push({
                    ...response.data[key]
                });
            }
        }
        this.setState({ reseps: data });
    }

    // shouldComponentUpdate(nextProps, nextState) {
    //     console.log("shouldComponentUpdate()");
    // }

    render() {
        console.log("render()");
        const lastIndex = this.state.currPage*this.state.perPage;
        const firstIndex = lastIndex - this.state.perPage;
        const resepsPerPage = this.state.reseps.slice(firstIndex,lastIndex);
        const paginate = (pageNumber) =>this.setState({currPage:pageNumber});
        return (
            <div className={classes.resepList}>
                <h1 className={classes.title}>All Reseps</h1>
                <Button onClick={this.handleAddResep} variant="primary">
                    Add Resep
                </Button>
                <div className={classes.SearchLayout}>
                    <input className={classes.SearchBar} onChange={this.handleInputChange} type="text" placeholder="Search..." aria-label="Search"/>
                </div>
                <div>
                    {this.state.reseps && resepsPerPage.map((resep) => (
                        <Resep
                            key={resep.noResep}
                            noResep={resep.noResep}
                            namaDokter={resep.namaDokter}
                            namaPasien={resep.namaPasien}
                            catatan={resep.catatan}
                            listObat={resep.listObat}
                            handleEdit={() => this.handleEditResep(resep)}
                            handleDelete={() => this.handleDeleteResep(resep.noResep)}
                        />
                    ))}
                </div>
                <Modal show={this.state.isCreate || this.state.isEdit} handleCloseModal={this.handleCancel}>
                    <form>
                        <h3 className={classes.modalTitle}>
                            {this.state.isCreate
                            ? "Add Resep"
                            : `Edit Resep Nomor ${this.state.noResep}`}
                        </h3>
                        <input
                            className={classes.textField}
                            type="text"
                            placeholder="Nama Dokter"
                            name="namaDokter"
                            value={this.state.namaDokter}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className={classes.textField}
                            type="text"
                            placeholder="Nama Pasien"
                            name="namaPasien"
                            value={this.state.namaPasien}
                            onChange={this.handleChangeField}
                        />
                        <textarea
                            className={classes.textField}
                            placeholder="Catatan"
                            name="catatan"
                            rows="4"
                            value={this.state.catatan}
                            onChange={this.handleChangeField}
                        />
                        <Button onClick={
                            this.state.isCreate
                                ? this.handleSubmitAddResep
                                : this.handleSubmitEditResep
                        } variant="primary">
                            {this.state.isCreate ? "Create" : "Edit"}
                        </Button>
                        <Button onClick={this.handleCancel} variant="danger">
                            Cancel
                        </Button>
                    </form>
                </Modal>
                <div className={classes.PaginationLayout}>
                    <Pagination total={this.state.reseps.length} perPage={this.state.perPage} paginate={paginate}/>
                </div>
            </div>
        );
    }
}
export default ResepList;