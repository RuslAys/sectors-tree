import React from "react";
import {Button, Container, Row} from "react-bootstrap";
import SelectField from "../form/SelectField";
import {parseRoots} from "../../helpers/sectors_helper";
import {Field, Form, Formik} from "formik";
import {NotificationManager} from "react-notifications";
import {deleteSector} from "../../api/SectorsApi";

function DeleteSectorsForm({roots, onDelete, onSelectNode}) {
    const initialValues = {
        sector: null
    };


    async function handleFormSubmit(values, formHelpers) {
        const answer = window.confirm("Are you sure?");
        if (!answer) {
            return
        }
        let node = values.sector;
        if (!node) {
            NotificationManager.error("Something gone wrong", "Error", 4000)
            return
        }
        deleteSector(node.id).then(() => {
            NotificationManager.success("Sector deleted", "Success", 4000)
            onDelete()
        })
    }

    const findNode = (sectors, id) => {
        if (!sectors || !id) {
            return null
        }
        for (let idx in sectors) {
            let sector = sectors[idx];
            if (sector.id === id) {
                return sector
            }
            let node = findNode(sector.children, id);
            if (node) {
                return node;
            }
        }
        return null;
    }

    return (
        <Formik
            initialValues={initialValues}
            onSubmit={handleFormSubmit}>
            {({setFieldValue, values}) => (
                <Form className="form">
                    <Container>
                        <Row>
                            <Field
                                component={SelectField}
                                name="sectors"
                                label="Sectors:"
                                data={parseRoots(roots)}
                                className="col col-md-12"
                                required={true}
                                onChange={(selectedOptions) => {
                                    let node = findNode(roots, selectedOptions[0])
                                    setFieldValue('sector', node);
                                    onSelectNode(node)
                                }}
                            />
                        </Row>
                        <Row>
                            <Button type="submit" variant="danger" size="sm" confirm>Delete</Button>
                        </Row>
                    </Container>
                </Form>
            )}
        </Formik>
    )

}

export default DeleteSectorsForm;
