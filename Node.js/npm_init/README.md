npm initを試すプロジェクト

    $ npm init
    This utility will walk you through creating a package.json file.
    It only covers the most common items, and tries to guess sensible defaults.

    See `npm help json` for definitive documentation on these fields
    and exactly what they do.

    Use `npm install <pkg> --save` afterwards to install a package and
    save it as a dependency in the package.json file.

    Press ^C at any time to quit.
    name: (npm_init) npm test project
    Sorry, name can only contain URL-friendly characters.
    name: (npm_init) npm_test_project
    version: (1.0.0) 
    description: npm init test.
    entry point: (index.js) 
    test command: 
    git repository: 
    keywords: 
    author: 
    license: (ISC) 
    About to write to /Users/ei/GitHub/Sample/Node.js/npm_init/package.json:

    {
      "name": "npm_test_project",
      "version": "1.0.0",
      "description": "npm init test.",
      "main": "index.js",
      "scripts": {
        "test": "echo \"Error: no test specified\" && exit 1"
      },
      "author": "",
      "license": "ISC"
    }


    Is this ok? (yes) yes

    $ npm install qrcode-console -save
    npm_test_project@1.0.0 /Users/ei/GitHub/Sample/Node.js/npm_init
    └── qrcode-console@0.1.0 

    npm WARN npm_test_project@1.0.0 No repository field.