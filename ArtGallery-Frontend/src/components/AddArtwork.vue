<template>
  <div>
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <div class="container">
      <div class="col">
        <div class="row">
          <div class="col mb-3">
            <div class="card">
              <div class="card-body">
                <div class="e-profile">
                  <div class="row">
                    <div class="col-12 col-sm-auto mb-3">
                      <div class="mx-auto" style="width: 500px">
                        <div
                          class="d-flex justify-content-center align-items-center rounded"
                          style="
                            height: 500px;
                            background-color: rgb(233, 236, 239);
                          "
                        >
                          <span
                            style="
                              color: rgb(166, 168, 170);
                              font: bold 8pt Arial;
                            "
                            >500x500</span
                          >
                        </div>
                      </div>
                    </div>
                    <div
                      class="col d-flex flex-column flex-sm-row justify-content-between mb-3"
                    >
                      <div class="text-center text-sm-left mb-2 mb-sm-0">
                        <div class="mt-2">
                          <button class="btn btn-primary" type="button">
                            <i class="fa fa-fw fa-camera"></i>
                            <span>Upload Picture</span>
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <ul class="nav nav-tabs">
                    <li class="nav-item">
                      <a href="" class="active nav-link">Parameters</a>
                    </li>
                  </ul>
                  <div class="tab-content pt-3">
                    <div class="tab-pane active">
                      <form class="form" novalidate="">
                        <div class="row">
                          <div class="col">
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Name</label>
                                  <input
                                    class="form-control"
                                    type="text"
                                    name="name"
                                    placeholder="Mona Lisa"
                                  />
                                </div>
                              </div>
                              <div class="col">
                                <div class="form-group">
                                  <label>Type of Artwork</label>
                                  <select
                                    class="form-control"
                                    name="typeOfArtwork"
                                  >
                                    <option value="Sculpture">Sculpture</option>
                                    <option value="Painting">Painting</option>
                                    <option value="Photography">
                                      Photography
                                    </option>
                                    <option value="Other">Other</option>
                                  </select>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Price</label>
                                  <input
                                    class="form-control"
                                    type="text"
                                    placeholder="$100.00"
                                  />
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col mb-3">
                                <div class="form-group">
                                  <label>Description</label>
                                  <textarea
                                    class="form-control"
                                    rows="5"
                                    placeholder="Description"
                                  ></textarea>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Is In Store?</label>
                                  <select class="form-control" name="isInStore">
                                    <option value="Yes">Yes</option>
                                    <option value="No">No</option>
                                  </select>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col d-flex justify-content-end">
                            <button class="btn btn-primary" type="submit">
                              Submit
                            </button>
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-12 col-md-3 mb-3">
            <div class="card mb-3">
              <div class="card-body">
                <div class="px-xl-3">
                  <button class="btn btn-block btn-secondary">
                    <i class="fa fa-sign-out"></i>
                    <span>Logout</span>
                  </button>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="card-body">
                <h6 class="card-title font-weight-bold">Support</h6>
                <p class="card-text">
                  Get fast, free help from our friendly assistants.
                </p>
                <button type="button" class="btn btn-primary">
                  Contact Us
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "EditProfile",
};
</script>

<style>
body {
  padding-top: 5rem;
  background: #f8f8f8;
}
</style>