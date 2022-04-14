import {Field, Form, Formik} from "formik";
import {Button, Container, Row} from "react-bootstrap";
import React from "react";
import {parseRoots} from "../../helpers/sectors_helper";
import SelectField from "../form/SelectField";
import InputField from "../form/InputField";
import {createSector, updateSector} from "../../api/SectorsApi";
import {NotificationManager} from "react-notifications";

function ManageSectorsForm({selectedSector, roots, onSave, onUpdate}) {

    const initialValues = {
        name: "",
        parent: {
            id: null
        }
    }

    const [initFormikValues, setInitFormikValues] = React.useState(initialValues);
    const [selectedParentId, setSelectedParentId] = React.useState();
    const [submitType, setSubmitType] = React.useState();

    React.useEffect(() => {
        if (selectedSector) {
            let copy = {...initFormikValues}
            copy.name = selectedSector.name
            setSelectedParentId(selectedSector.parent)
            setInitFormikValues(copy)
        }
    }, [selectedSector])

    async function handleFormSubmit(values, formHelpers) {
        if (submitType === "save") {
            let copy = {...values}
            if (selectedParentId) {
                copy.parent = {id: selectedParentId}
            }
            createSector(copy).then(() => {
                NotificationManager.success("Sector saved", "Success", 4000)
                onSave()
            })
        } else if (submitType === "update" && selectedSector) {
            let copy = {...values}
            copy.id = selectedSector.id
            copy.parent = {id: selectedParentId}
            updateSector(copy).then(() => {
                NotificationManager.success("Sector updated", "Success", 4000)
                onUpdate()
            })
        }
        setInitFormikValues(initialValues)
        formHelpers.resetForm(initialValues)
    }

    return (
        <Formik
            enableReinitialize={true}
            initialValues={initFormikValues}
            onSubmit={handleFormSubmit}>
            {({setFieldValue, values}) => (
                <Form className="form">
                    <Container>
                        <Row>
                            <Field
                                component={SelectField}
                                name="parent.id"
                                label="Select parent"
                                data={parseRoots(roots)}
                                className="col col-md-12"
                                value={selectedParentId}
                                onChange={(selectedOptions) => {
                                    setFieldValue('parent.id', selectedOptions[0]);
                                    setSelectedParentId(selectedOptions[0])
                                }}
                            />
                        </Row>
                        <Row>
                            <Field
                                component={InputField}
                                name="name"
                                label="Sector name"
                                placeholder="Input sector name"
                                className="col col-md-12"
                                required
                                onChange={(event) => {
                                    setFieldValue('name', event.target.value);
                                }}
                            />
                        </Row>
                        <Row>
                            <Button type="submit" variant="primary" size="sm" onClick={() => {
                                setSubmitType("save")
                            }}>Save</Button>
                            <Button type="submit" variant="info" size="sm" className="ml-2" onClick={() => {
                                setSubmitType("update")
                            }}>Update</Button>
                        </Row>
                    </Container>
                </Form>
            )}
        </Formik>
    )
}

export default ManageSectorsForm;