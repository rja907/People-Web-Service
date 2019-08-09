import React from 'react';
import ReactDOM from 'react-dom';
import './App.css';

class Table extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            foo: []
        };
    }

    componentDidMount() {
        // this.hello()
        fetch('/api/requiredUsers')
            .then(response => response.json())
            .then(data => this.setState({ foo: data }));
    }

    render() {
        return (
            <div>
                <h1 style={{textAlign: 'center'}}>5 youngest users sorted by name</h1>
                <table className="table" id='students'>
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Age</th>
                        <th scope="col">Number</th>
                        <th scope="col">Bio</th>
                        <th scope="col">Photo</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.foo.map(person => (
                        <tr key={person.id}>
                            <th scope="row">{person.id}</th>
                            <td>{person.name}</td>
                            <td>{person.age}</td>
                            <td>{person.number}</td>
                            <td>{person.bio}</td>
                            <td><img src={person.photo} alt="new" /></td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Table;

ReactDOM.render(<Table />, document.getElementById('root'));
