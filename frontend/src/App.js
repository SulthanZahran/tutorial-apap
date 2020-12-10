import React from "react";

import List from "components/List";
import listMovies from "movies.json";
import "./App.css"
import EmptyState from "./components/EmptyState";
import Item from "./components/Item";

export default class App extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            favItems: [],
            toogleStat: false,
        };
        this.handleToggle = this.handleToggle.bind(this)
    }

  render() {
      const { favItems } = this.state;

    return (
        <div className="container-fluid">
          <h1 className="text-center mt-3 mb-0">Favorites Movie App</h1>
          <p className="text-center text-secondary text-sm font-italic">
            (This is a <strong>class-based</strong> application)
              <div><button type="button" className="btn btn-danger" onClick={this.removeAll}>Remove All Favorite</button></div>
          </p>
            <div className="d-flex justify-content-center">
                <label className="switch" align="center">
                    <input type="checkbox" onClick={this.handleToggle}/>
                    <span className="slider round "></span>
                </label>
            </div>

          <div className="container pt-3">
            <div className="row">
              <div className={"col-sm"}>
                  <List
                    title="List Movies"
                    items={listMovies}
                    onItemclick={this.handleAddFavorite}
                    checkbox={false}
                  />
              </div>
                {!this.state.toogleStat ?
                    <div className={"col-sm"}>
                        <List
                            title="My Favorites"
                            items={favItems}
                            onItemclick={this.handleRemoveFavorite}
                            checkbox={true}
                        />
                    </div>
                    : null}
            </div>
          </div>
        </div>
    );
  }

    handleAddFavorite = item => {
        const newItems = [...this.state.favItems];
        const newItem = {...item};
        const targetInd = newItems.findIndex(it => it.id === newItem.id);
        if(targetInd < 0) newItems.push(newItem);
        this.setState({favItems:newItems});
    }

    handleRemoveFavorite = item=> {
        const newItems = [...this.state.favItems];
        const newItem = {...item};
        const targetInd = newItems.findIndex(it=>it.id===newItem.id);
        if(targetInd<0) newItems.push(newItem);
        else newItems.splice(targetInd, 1);
        this.setState({favItems: newItems});
    }

    handleToggle = () => {
        const {toogleStat} = this.state;
        this.setState({toogleStat : !toogleStat});
    }

    removeAll = () => {
        this.setState({favItems: []});
    };

}

