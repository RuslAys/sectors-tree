import React from 'react'

function SelectField({
                         field,
                         label,
                         data,
                         form,
                         className,
                         multiple,
                         required,
                         value,
                         ...props
                     }) {

    const onChange = (event) => {
        let options = event.target.options;
        let selectedOptions = [];
        for (let i = 0, l = options.length; i < l; i++) {
            if (options[i].selected) {
                let opt = parseInt(options[i].value)
                selectedOptions.push(opt);
            }
        }
        props.onChange(selectedOptions);
    };

    const createOptions = (data, shift) => {
        if (data) {
            return data.map(s =>
                <>
                    <option key={s.value} value={s.value} selected={value === s.value}>{shift + s.label}</option>
                    {
                        createOptions(s.children, shift + '\u00A0\u00A0\u00A0\u00A0')
                    }
                </>)
        }
        return <></>
    }

    return (
        <div className="form-row">
            <div className={className}>
                <div className="form-group">
                    <label>{label}</label>
                    <br/>
                    <select size={5} onChange={onChange} multiple={multiple} required={required}>
                        {
                            createOptions(data, '')
                        }
                    </select>
                    <span>
                        {form && field && form.touched[field.name] && form.errors[field.name] && (
                            <span>{form.errors[field.name]}</span>
                        )}
                      </span>
                </div>
            </div>
        </div>
    );
}

export default SelectField;
