const {createMockServer} = require("grpc-mock");
const mockServer = createMockServer({
    protoPath: "../../../backend/src/main/proto/master_data_service.proto",
    packageName: "com.coats.d3.masterdata",
    serviceName: "MasterDataService",
    rules: [
        {
            method: "GetSalesOrg",
            input: {code: "IN50"},
            output: {code: "IN50", name: "Coats India", company_code: "TEst",},
        },
        {method: "GetSalesOrg", input: {code: "1234"}, error: {code: 5, message: "Data not found"}},
        {method: "GetSalesOrg", input: {code: "ABC"}, error: {code: 14, message: "ERROR"}}
    ]
});
mockServer.listen("0.0.0.0:8123");