import React from 'react';
import {Vocable} from "./Vocable.jsx";

export class VocableList extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const id = this.props.id;
        const name = this.props.name;
        const blockDescription = this.props.blockDescription;
        const showPoints = this.props.showPoints;
        const vocables = this.props.list && <table>
            <thead>
            <tr>
                <th>Word</th>
                {!blockDescription && <th>Description</th>}{showPoints && <th>Points</th>}
            </tr>
            </thead>
            <tbody>{this.props.list.map(vocable => <Vocable key={"word".concat(vocable.id)}
                                                            vocable={vocable.vocable}
                                                            description={vocable.description}
                                                            points={vocable.points}
                                                            blockDescription={blockDescription}
                                                            showPoints={showPoints}/>
            )}</tbody>
        </table>;
        return (
            <div>
                {id && <div>Id: {id}</div>}
                {name && <div>Name: {name}</div>}
                {vocables && <div>{vocables} </div>}
            </div>
        )
    }

}

VocableList.defaultProps = {
    blockDescription: true,
    showPoints: false,
};