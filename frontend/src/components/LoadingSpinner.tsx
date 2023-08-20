export default function LoadingSpinner() {
    return (
        <div
            className='d-flex justify-content-center align-items-center spinner-border text-primary'
            role='status'
        >
            <span className='sr-only'>Loading...</span>
        </div>
    );
}
