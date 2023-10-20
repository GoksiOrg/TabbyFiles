import { FallbackProps } from 'react-error-boundary';

export default function GlobalError(props: { fallbackProps: FallbackProps }) {
    const error = props.fallbackProps.error as Error;
    const resetError = props.fallbackProps.resetErrorBoundary;
    return (
        <div className='flex-center flex-column'>
            <div className='alert alert-danger flex-column border border-3'>
                <h4 className='alert-heading'>Unexpected error happened !</h4>
                <p>{error.message}</p>
                <hr />
                <p>Refresh page or click try again !</p>
            </div>
            <button type='button' className='mb-3 btn btn-outline-danger' onClick={resetError}>
                Try again !
            </button>
        </div>
    );
}
