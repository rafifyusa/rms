function showInsertModal () {
    var dialog = document.getElementById('insertModal');
    var showModalButton = document.getElementById('insertButton');
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }
    showModalButton.addEventListener('click', function() {
        dialog.showModal();
    });
    dialog.querySelector('.close').addEventListener('click', function() {
        dialog.close();
    });
}

function showEditModal(modalId) {
    var dialog = document.getElementById('editModal'+modalId);
    var showModalButton = document.getElementById('editButton'+modalId);
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }
    showModalButton.addEventListener('click', function() {
        dialog.showModal();
    });
    dialog.querySelector('.close').addEventListener('click', function() {
        dialog.close();
    });
}

/*
(function () {
    'use strict';
    var snackbarContainer = document.querySelector('#demo-snackbar-example');
    var showSnackbarButton = document.querySelector('#demo-show-snackbar');
    var handler = function(event) {
        showSnackbarButton.style.backgroundColor = '';
    };
    showSnackbarButton.addEventListener('click', function() {
        'use strict';
        showSnackbarButton.style.backgroundColor = '#' +
            Math.floor(Math.random() * 0xFFFFFF).toString(16);
        var data = {
            message: 'Button color changed.',
            timeout: 2000,
            actionHandler: handler,
            actionText: 'Undo'
        };
        snackbarContainer.MaterialSnackbar.showSnackbar(data);
    });
}());
*/
